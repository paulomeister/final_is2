FROM node:latest

WORKDIR /app

# Copiar sólo package.json y package-lock.json (si existe) para aprovechar cache de Docker
COPY package*.json ./

# Instalar dependencias (incluye devDependencies) para desarrollo
RUN npm install

# Copiar todo (el código será sobrescrito por el volumen en docker-compose durante desarrollo)
COPY . .

# Puerto por defecto del servidor de desarrollo Angular
EXPOSE 4200

# Comando por defecto para desarrollo: levantar angular dev server en host 0.0.0.0
# Ejecutamos `npm run start -- --host 0.0.0.0 --port 4200` para asegurarnos de que el dev server
# esté accesible desde fuera del contenedor sin modificar package.json.
CMD ["npm", "run", "start", "--", "--host", "0.0.0.0", "--port", "4200"]
