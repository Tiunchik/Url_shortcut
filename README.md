## Url_shortcut

[![Build Status](https://travis-ci.org/Tiunchik/job4j_url_shortcut.svg?branch=master)](https://travis-ci.org/Tiunchik/job4j_url_shortcut)

### Overall descripiton:
The main idea of the task is to create the REST service that keeps URLs for your site pages and gives them back on demand.
A user who wants to store links of his site has to register {/register} and then, after receiving JWT token for accessing he has to login {/login} and can start converting URLs of his site to the service codes {/convert}.
After converting anyone can get access to the site pages via service codes. The service has a counter of loading pages {/statistic}. Only a signed user has access to statistic of his site.

### Used the following modules: 
* Spring Boot(+ security, data(hibernate), web)
* PostgreSQL
* Liquebase (create all database on the first start)
