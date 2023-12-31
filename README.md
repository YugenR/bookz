# BOOKz - Quick Start Guide

## Welcome to BOOKz!

BOOKz is an awesome application that [provide a brief description of your application].

### Prerequisites

Before you get started, make sure you have the following installed on your machine:

- **Docker**: [Docker Installation Guide](https://docs.docker.com/get-docker/)

### Setup

To run BOOKz, follow these steps:

1. Open a terminal in the root directory of the project.

2. Run the following command to start a MySQL container for BOOKz:

    ```bash
    docker run --name mysql_bookz --env-file ./src/main/resources/bookz-mysql.env -p 3306:3306 -d mysql:latest
    ```

   This command creates a MySQL container named `mysql_bookz` with the provided environment file and exposes port 3306.

3. Run Spring application 
