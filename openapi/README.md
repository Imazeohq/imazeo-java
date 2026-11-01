# OpenAPI snapshot

This SDK ships a handwritten public API. When refreshing contracts:

1. Export backend OpenAPI (`/docs/openapi.json`) from a reviewed release
2. Store snapshot here (e.g. `backend.json`) and update `contract-meta.json`
3. Diff models against resources; adjust handwritten types as needed
4. Run `mvn test`

Do not ship raw OpenAPI-generator clients as the primary public API.
