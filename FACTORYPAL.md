# Fork
We created this fork of https://github.com/allegro/hermes  to 
- add our own authentication
- simplify configuration by introducing our own environment variables 

# Local development
Use docker compose to bring up the hermes containers with zookeeper, kafka and schema registry:
`docker compose up -d`