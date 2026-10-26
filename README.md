# CIA 2 – End-to-End CI/CD Pipeline on Microsoft Azure

A small Java (Spring Boot) web app that is **built, tested, containerised and deployed automatically**
to Azure Kubernetes Service every time code is pushed to `main`.

```
git push ──► GitHub Actions
               1. Build & Test   (Maven + JUnit)
               2. Docker Build & Push ──► Azure Container Registry (vidyuthdevopsacr)
               3. Deploy (Ansible + kubectl) ──► Azure Kubernetes Service (cia2-aks-cluster)
               4. Verify (rollout status + curl the public IP)
                                              └──► http://<EXTERNAL-IP>  (browser)
```

## Tools
| Purpose | Tool |
|---|---|
| Source control | Git + GitHub |
| Build & unit tests | Maven, JUnit 5, Spring MockMvc |
| Containerisation | Docker (`eclipse-temurin:17-jre-alpine`) |
| Image registry | Azure Container Registry (Basic) |
| Orchestration | Azure Kubernetes Service (1 node, 2 pods) |
| CI/CD | GitHub Actions |
| Deployment automation | Ansible (`ansible/deploy.yml`) |
| Cloud login | OIDC federated identity (no stored passwords) |

## Repository layout
| Path | What it is |
|---|---|
| `pom.xml` | Maven build file (dependencies, Java 17, builds `target/app.jar`) |
| `src/main/java/.../GreetingService.java` | App logic – **`APP_VERSION` is the line changed in the demo** |
| `src/main/java/.../HomeController.java` | Web endpoints: `/`, `/version`, `/health` |
| `src/test/java/...` | 6 automated tests (3 unit, 3 web-layer) |
| `Dockerfile`, `.dockerignore` | Container image definition |
| `k8s/deployment.yaml` | 2 replicas, rolling update, health probes |
| `k8s/service.yaml` | Azure Load Balancer with a public IP |
| `ansible/deploy.yml`, `ansible/inventory.ini` | Deployment playbook run by the pipeline |
| `.github/workflows/ci-cd.yml` | The pipeline |

## Endpoints
* `/` – home page showing the current version
* `/version` – plain-text version (used by the verify stage)
* `/health` – returns `UP` (used by Kubernetes probes)

## Demo: push a change
1. In `GreetingService.java` change `APP_VERSION = "Version 1"` to `"Version 2"`.
2. In `GreetingServiceTest.java` change the expected value to `"Version 2"`.
3. `git commit -am "Release Version 2" && git push`
4. Watch the **Actions** tab – all 4 jobs go green – then refresh `http://<EXTERNAL-IP>`.

*Failure demo:* change only step 1, push → job 1 fails on the test, nothing is deployed, the old version stays live.

## Run locally
```
mvn clean verify          # build + tests
java -jar target/app.jar  # open http://localhost:8080
```
