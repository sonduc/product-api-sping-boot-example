# Working in this repository

- Establish the requested scope and acceptance criteria. Check the branch, HEAD, and local changes before editing. Preserve changes outside the task.
- Start with the [project README](README.md) for the stack and local workflow. Use the [documentation map](docs/README.md) to find the relevant module and the [architecture guide](docs/ARCHITECTURE.md) to follow the code.
- Use the [coding rules](docs/CODING_RULES.md) for shared conventions and the [development guide](docs/DEVELOPMENT.md) for the devcontainer setup.
- For Product changes, use the [Product testing guide](docs/modules/product/TESTING.md). For changes across modules or to shared configuration, use the [project testing guide](docs/TESTING.md).
- Before changing behavior, trace its normal entry point through relevant configuration and dependencies. Compare behavior claims with source and test assertions. Flag discrepancies between requirements and implementation.
- Keep commands and checks within the requested scope. Record what ran, the result, and any prerequisites that prevented verification. Review the final diff before handoff.
