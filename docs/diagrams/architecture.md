# Project Architecture

```mermaid
graph TD
    subgraph Frontend[React Frontend]
        UI[React Components]
        State[Redux Store]
        API[API Client]
        UI --> State
        State --> API
    end

    subgraph Backend[Spring Boot Backend]
        Controllers[REST Controllers]
        Services[Business Services]
        Repositories[Data Repositories]
        Controllers --> Services
        Services --> Repositories
    end

    subgraph Database
        H2[In-Memory H2 Database]
    end

    subgraph External Services
        AWS[AWS Services]
        AWS --> "S3 Storage"
        AWS --> "CloudWatch"
    end

    Frontend --> Backend
    Backend --> Database
    Backend --> AWS

    style Frontend fill:#f9f,stroke:#333,stroke-width:2px
    style Backend fill:#bbf,stroke:#333,stroke-width:2px
    style Database fill:#bfb,stroke:#333,stroke-width:2px
    style ExternalServices fill:#fbb,stroke:#333,stroke-width:2px
```

## Component Details

### Frontend Architecture
```mermaid
graph TD
    subgraph Pages
        Dashboard[Dashboard]
        Communities[Communities]
        Events[Events]
        FoodTrucks[Food Trucks]
        Profile[Profile]
    end

    subgraph Components
        Layout[Layout]
        Navigation[Navigation]
        Cards[Card Components]
        Forms[Form Components]
    end

    subgraph State Management
        Redux[Redux Store]
        Auth[Authentication]
        UI[UI State]
    end

    Pages --> Components
    Components --> State Management
    State Management --> Redux
    Auth --> Redux
    UI --> Redux

    style Pages fill:#f9f,stroke:#333,stroke-width:2px
    style Components fill:#bbf,stroke:#333,stroke-width:2px
    style StateManagement fill:#bfb,stroke:#333,stroke-width:2px
```

### Backend Architecture
```mermaid
graph TD
    subgraph Authentication
        Login[Login]
        Register[Register]
        JWT[JWT Token]
        Login --> JWT
        Register --> JWT
    end

    subgraph FoodTruck
        FT_Create[Create]
        FT_Read[Read]
        FT_Update[Update]
        FT_Delete[Delete]
    end

    subgraph Community
        C_Create[Create]
        C_Read[Read]
        C_Update[Update]
        C_Delete[Delete]
    end

    subgraph Event
        E_Create[Create]
        E_Read[Read]
        E_Update[Update]
        E_Delete[Delete]
    end

    Authentication --> JWT
    FoodTruck --> FT_Create
    FoodTruck --> FT_Read
    FoodTruck --> FT_Update
    FoodTruck --> FT_Delete
    Community --> C_Create
    Community --> C_Read
    Community --> C_Update
    Community --> C_Delete
    Event --> E_Create
    Event --> E_Read
    Event --> E_Update
    Event --> E_Delete

    style Authentication fill:#f9f,stroke:#333,stroke-width:2px
    style FoodTruck fill:#bbf,stroke:#333,stroke-width:2px
    style Community fill:#bfb,stroke:#333,stroke-width:2px
    style Event fill:#fbb,stroke:#333,stroke-width:2px
```

## Data Flow
```mermaid
sequenceDiagram
    participant User
    participant Frontend
    participant Backend
    participant Database

    User->>Frontend: Make API Request
    Frontend->>Backend: Send Request
    Backend->>Database: Query Data
    Database-->>Backend: Return Data
    Backend-->>Frontend: Send Response
    Frontend-->>User: Display Data

    User->>Frontend: Submit Form
    Frontend->>Backend: Send Data
    Backend->>Database: Store Data
    Database-->>Backend: Confirm
    Backend-->>Frontend: Success Response
    Frontend-->>User: Update UI
```
