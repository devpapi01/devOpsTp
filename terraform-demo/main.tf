terraform {
  required_providers {
    docker = {
      source  = "kreuzwerker/docker"
      version = "~> 3.0"
    }
  }
}

provider "docker" {
  host = "npipe:////./pipe/docker_engine"
}

# Créer une image Nginx
resource "docker_image" "nginx" {
  name = "nginx:latest"
}

# Créer un conteneur Nginx
resource "docker_container" "web" {
  name  = "terraform-nginx"
  image = docker_image.nginx.image_id

  ports {
    internal = 80
    external = 9091
  }
}

# Créer une image MySQL
resource "docker_image" "mysql" {
  name = "mysql:8.0"
}

# Créer un conteneur MySQL
resource "docker_container" "db" {
  name  = "terraform-mysql"
  image = docker_image.mysql.image_id

  env = [
    "MYSQL_ROOT_PASSWORD=root123",
    "MYSQL_DATABASE=testdb"
  ]

  ports {
    internal = 3306
    external = 3306
  }
}
