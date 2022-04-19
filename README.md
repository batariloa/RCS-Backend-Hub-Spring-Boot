
# Remote Command Service - A remote control solution for Linux PC

REST API that allows the users to remotely control their computer, using a registered account. 
Some of the available commands are: starting torrent downloads, shutting off the computer, sending commands to the bash terminal. 


## GENERAL

This project consists of three components:

### The Hub

This component is hosted on a server, and it serves as a hub. It authenticates users, using Spring Security and JWT, using an API call to "/authenticate". with user credentials.
After authenticated, this service connect the user to the proper PC, using the username as an indentificator. 

When the frontend requests a command to be executed on the users machine, the hub queues the command inside a Kafka MQ server.

This component stores and manages statistics sent by users, provided by the local service, running on the client's PC.
Status objects are being stored in HashMaps, with the key corresponding to the username.


### The Local Service

The local service is a service that runs on the client's PC. It connects to the hub service, and consumes the commands JMS from the "commands" topic inside the Kafka MQ server. Before connecting to the MQ, the data gathered from the local machine is being sent to the hub, stored in a MySQL database for statistics.

This service is the only one a consumer requires. It requires a configuration file, that sets the client's username, so that the appropriate commands can  be executed.

Source code can be found [here](https://github.com/batariloa/RCS-Local-Service).

### The Frontend

The frontend is written in AngularJS/Typescript and is composed of the most basic login form, and the control pannel. A service called api-service is designated for all calls to our REST API (hub).
Upon authentication, our api-service claims the JWT token, and uses it for further authenticated API calls.

Source code can be found [here](https://github.com/batariloa/RCS-Frontend-AngularJS).

# Authentication

Authentication is provided by Spring Security, using a JWT filter. The user credentials are stored in MySQL server in the cloud.

## Spring security configuration

```
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.
				cors()
				.and()
				.csrf()
				.disable()
				.authorizeRequests()
				.antMatchers("/authenticate")
				.permitAll()
				.anyRequest()
				.authenticated()
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

	}
```
Note that we are using BCryptPasswordEncoder for simplicity.

## JWT setup

The class JWTUtility represents a set of useful methods for extracting token data. For example:

```

   public String getUsernameFromToken(String token) {  			//returns username from the token
        return getClaimFromToken(token, Claims::getSubject);   
    }

```
On the other hand, our JWTFilter uses these methods to invoke authentication. It does this by overwriting the OncePerRequestFilter's following method:

```
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain)
            throws ServletException, IOException {
        String authorization = httpServletRequest.getHeader("Authorization");   //Our token must contain this header
        String token = null;
        String userName = null;

        if(null != authorization && authorization.startsWith("Bearer ")) { 	//This header starts with 'Bearer ' in every token
            token = authorization.substring(7);
            userName = jwtUtility.getUsernameFromToken(token);
        }

        if(null != userName && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails
                    = userService.loadUserByUsername(userName);

            if(jwtUtility.validateToken(token,userDetails)) {					//if the token is valid, get the users authorities
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                        = new UsernamePasswordAuthenticationToken(userDetails,
                        null, userDetails.getAuthorities());

                usernamePasswordAuthenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(httpServletRequest)
                );

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);   // hand the token to Spring Security
            }

        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
```

![Form](screenshots/form)


# Frontend 

The frontend for this application is created in AngularJS/Typescript, and can be found [here](https://github.com/batariloa/Remote-Status-Service-Frontend-AngularJS). 


![Frontend design](screenshots/remote)




