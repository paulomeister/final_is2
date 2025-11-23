terraform {
  required_providers {
    render = {
      source  = "renderinc/render"
      version = "0.1.0"
    }
  }
}

provider "render" {
  api_key = var.render_api_key
}

variable "render_api_key" {
  description = "API key para Render almacenada de forma segura"
  type        = string
}

resource "render_service" "crud_backend_canary" {
  name   = "crud-backend-canary"
  type   = "web_service"
  repo   = "https://github.com/paulomeister/final_is2.git"
  env    = "docker"
  plan   = "starter"
  branch = "canary"

  start_command = "java -jar target/serviciudad-Backend-0.0.1-SNAPSHOT.jar"

  auto_deploy = true
}

resource "render_service" "crud_backend_prod" {
  name   = "crud-backend-prod"
  type   = "web_service"
  repo   = "https://github.com/paulomeister/final_is2.git"
  env    = "docker"
  plan   = "starter"
  branch = "master"

  start_command = "java -jar target/serviciudad-Backend-0.0.1-SNAPSHOT.jar"

  auto_deploy = true
}
