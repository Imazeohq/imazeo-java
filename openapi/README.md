# OpenAPI snapshot

This SDK ships a handwritten public API. When refreshing contracts:

1. Export OpenAPI from the published Imazeo API (or a reviewed release artifact)
2. Store the API-key snapshot here (e.g. `backend.json`) and update `contract-meta.json`
3. Diff models against resources; adjust handwritten types as needed
4. Run `mvn test`

Do not expand the public SDK surface to admin or customer JWT portal APIs.
Do not ship raw OpenAPI-generator clients as the primary public API.
