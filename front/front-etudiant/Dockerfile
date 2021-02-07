FROM node:14 as build
WORKDIR /app
COPY package.json ./
RUN npm install
RUN npm install react-scripts -g
COPY . .
ARG REACT_APP_PROD_API
ENV REACT_APP_PROD_API=${REACT_APP_PROD_API}
RUN npm run build

FROM nginx:latest
WORKDIR /usr/share/nginx/html
COPY --from=build /app/build /usr/share/nginx/html
EXPOSE 80
