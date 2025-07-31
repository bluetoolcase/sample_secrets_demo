FROM node:22-alpine
RUN mkdir -p /opt/js_saas
WORKDIR /opt/js_saas
# Copy project files
COPY ./js_saas/ .

RUN npm install

RUN npm run build

EXPOSE 3000
COPY --chmod=777 ./infrastructure/js_saas/docker-entrypoint.sh ./

# Run the app
ENTRYPOINT ["./docker-entrypoint.sh"]
