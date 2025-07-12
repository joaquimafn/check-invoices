# Stage 1: Build angular application
FROM node:20-alpine as build

WORKDIR /app

COPY package*.json ./
RUN npm install

COPY . .
RUN npm run build -- --output-path=./dist/frontend

# Stage 2: Serve application from Nginx
FROM nginx:alpine

COPY --from=build /app/dist/frontend /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80
