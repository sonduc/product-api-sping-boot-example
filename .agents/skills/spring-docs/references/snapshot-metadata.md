# Snapshot, labels, and verification records

Use this reference for requested metadata edits, legacy records, and after-merge reconciliation. Map these meanings to the project's existing format and vocabulary; preserve unknown fields and unrelated records. Do not migrate the whole documentation tree.

## Fallback record

If the project has no convention, propose one Markdown `Verification` block with `<!-- spring-docs:verification:start -->` and `<!-- spring-docs:verification:end -->` on separate lines. Put the following applicable meanings inside that single block; use compact prose or lists, not a new config/schema dependency.

| Field | Meaning |
| --- | --- |
| `document_status` | Draft or maintained (or the project's terms); not a release assertion. Retain draft until the requested readiness conditions are evidenced. |
| `source_snapshot` | Evidence mode, exact ref/full base commit, capture time, and relevant uncommitted paths/status with hashes or retained patch. For committed evidence, full target commit suffices. Never label the whole working tree as one commit. |
| `code_review` | Status, scope, reviewed snapshot, evidence location/source, time if known, and limits. Use unknown/unconfirmed when evidence is missing. Changed bytes outside a reviewed commit are not covered automatically. |
| `verification_status` | Unverified, partial, or complete within the declared documentation scope, explicitly qualified by method (source comparison, runtime checks, etc.). Complete source comparison does not mean runtime was tested or code reviewed. |
| `code_scope` | Relative files/directories and the material claims covered; describe behavior when a directory is broad. |
| `dependencies` | Relevant shared code/config outside that scope. Empty means assessed and none found; use unknown when unassessed. |
| `verified_commit` | Full committed target against which all material claims in scope were checked. Null/unknown for incomplete checks or working-tree evidence. A base HEAD, future docs commit, or old snapshot is not current verification. |
| `verified_at` | Actual completed committed-target check time, ISO 8601 with timezone. Null/unknown with an unverified commit. A complete working-tree source comparison can record its time/method under `source_snapshot`/`verification_status` without claiming committed verification. |
| `content_updated_at` | Actual substantive content change time, including meaningful source-reference changes. Preserve on metadata-only refresh; mark unknown historical times rather than guessing. |
| `verification_limits` | Checked claims/methods and inaccessible evidence, missing dependencies, unrun tests/renderers, or business conflicts. Cite execution evidence only for its actual snapshot. |

Retain any previous verification record explicitly as historical within the existing convention (or the single fallback block) if it cannot describe the current document. A partial update may be delivered with limits. Do not keep a stale `verified_commit` in a field that appears to verify the current document. Do not make multiple fallback blocks: translation hashing requires exactly one identifiable block.

## Legacy metadata

- A prior verified commit establishes a comparison baseline only if available and applicable; it does not certify current prose, scope, dependencies, review, or tests. Missing labels are unknown, not implicitly reviewed/maintained.
- Inspect the requested claims as a first verification when metadata/scope is missing or unavailable. Keep unavailable or disputed historical evidence clearly labeled; do not manufacture a commit, time, review event, or empty dependency assessment.
- A missing dependency leaves its delegated behavior unknown. A caller without its own validation is not proof of a validation bug when the unavailable callee may implement it; distinguish missing evidence from an independently observed business violation.
- Repeated/malformed blocks or incompatible old fields need an explicit scoped normalization plan. Preserve the old evidence and do not guess which block to exclude for translation hashing. Use exact EN commit/path lineage when reliable; for uncommitted ambiguous EN, report lineage unresolved until it can be made unambiguous. No fabricated Git identity for uncommitted content.
- A verification-only refresh leaves body content time and translation identity/time unchanged. New status/snapshot fields inside the one excluded block do not alone create a new EN content version. Follow the entrypoint's translation rules for body/source-reference changes.

## Requested reconciliation after merge

1. Resolve the configured local main/master ref to a full commit and state freshness/release limits. Keep the same destination paths. Inspect their current bytes and differences from the pinned snapshot to preserve user work.
2. Compare prior verified evidence and any draft snapshot with that target, including other authors' changes, shared dependencies, rename/delete, flowcharts, and links. An empty diff or merge signal alone is insufficient; trace all material claims within the requested scope. Explain divergent histories and confirm the intended comparison before changing verification records.
3. Check approved business requirements as well as observed code. If code violates them, report source/document locations, expected versus observed behavior, and the affected claims. Stop those content changes; do not document the bug as intended behavior, change requirements, silently repair code, or mark the affected document complete. Replacing an unmet business claim with a weaker implementation fact (for example, a reserve call without any reservation) does not resolve the conflict. Keep the affected claim unresolved and verification partial until the business/code discrepancy is resolved; this applies to drafts as well as after-merge work. Continue independent authorized work; a metadata update may record partial/conflict status only when authorized.
4. Advance verification only after a complete scoped check of the selected target. Keep source comparison, runtime checks, and code review evidence separate. Reassess old review scope against the actual merged bytes; do not invent review from merge/test results. Retain draft or other unresolved readiness labels when the required evidence is missing. Changing a draft label is a separate judgment supported by the request/project criteria, never an automatic merge action.
