## How to set up and run the application

### Set up

Clone or pull main repo with git: 
**git clone git@github.com:ev-stepanov/avito_parser.git**

Download Docker and Docker Compose. Run Docker.

Open project with *pom.xml* file in the root directory of project

Open Terminal or Command Line to run docker-compose:
1. Go to “backend” folder
2. Type "docker-compose up -d" to boot containers
3. If you want to restart containers, type “docker-compose down” and then "docker-compose up -d"

* Module load_ads responsible for parsing advertising from avito.
* Module save_ads responsible for saving advertising to database (postgres).
* Module model save dto and entity
