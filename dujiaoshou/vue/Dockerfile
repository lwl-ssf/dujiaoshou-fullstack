# Frontend Dockerfile (build + nginx serve)
FROM node:18 AS build
WORKDIR /app
COPY package.json package-lock.json* pnpm-lock.yaml* yarn.lock* ./ 2>/dev/null || true
RUN npm i --silent
COPY . .
RUN npm run build

FROM nginx:alpine
COPY --from=build /app/dist /usr/share/nginx/html
EXPOSE 80
CMD ["nginx","-g","daemon off;"]

# Use custom nginx config with /api reverse proxy
COPY nginx.conf /etc/nginx/nginx.conf
