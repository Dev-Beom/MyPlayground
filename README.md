# Spring-Playground
Java/Kotlin Spring Framework Study.

## Docker Container Setting
### Redis
#### start a redis instance
`docker run --name some-redis -d redis`

#### start with persistent storage
`docker run --name some-redis -d redis redis-server --save 60 1 --loglevel warning`

#### connecting via redis-cli
`docker run -it --network some-network --rm redis redis-cli -h some-redis`

#### example
`docker run --name redis-container -d -p 6379:6379 redis`
