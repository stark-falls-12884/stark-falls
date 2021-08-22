REAP take home assignment
Author: James Wong
Email: winghojameswong@gmail.com

Deliverables:
Production URL: https://stark-falls-12884.herokuapp.com
Source Code: https://github.com/stark-falls-12884/stark-falls
Documentation: This file
Backend API doc(Generated): https://stark-falls-12884.herokuapp.com/swagger-ui/index.html

How to run(from repo root):
- Environment requirement, JDK 8+, gradle, npm, yarn, assuming *nix commandline environment, port: 8080, 3000 should not be in use
- Please prepare a folder for uploaded file storage which that server process owner have access to, for example /Users/jameswong/instapic/file-upload
- To start backend dev server
- cd ./instapic
- Run command
        gradle bootRun --args="--local-file-service.uploadDirectory=<your filepath>"
- Double check if the uploadDirectory is set correctly
- Spring server should be started
- To start frontend dev server
- Leave spring boot running, open up another terminal, change directory back to repo root.

- cd ./frontend
- Run command
        yarn install
        yarn dev
- Frontend dev server should be started, go to http://localhost:3000 to start

Key feature documentation:
User authentication and authorization
- In this app they are the same thing, all users can access all features
- In backend we use Spring Security, although it is designed for traditional MVC login flow, with some configuration we can turn it into RESTFUL login
- User are identified with cookies that contains the session key which is handled by Spring security
- User info validation is handled by @Valid annotation on the UserLoginRequest

Image Storage
- In this app for simplicity, image are stored locally, storage location is controlled by application.properties local-file-service.uploadDirectory
- To prevent same filename overwrite, on file upload a UUID is generated to use as the saved filename, the original filename and mimetype is stored in database
- LocalFileController will allow the user to retrieve this file by UUID, the image url is stored by LocalFileService in database
- Once user retrieves PostView, PostView.imageUrl will be the url that hits LocalFileController for the image itself

Post Creation
- Spring JPA takes Pageable object in for page and sorting
- PostService will inject Current(server) time for current user for filtering/ sorting

Redux/Router integration
-The state flow is from History.state(if any) -> Redux state -> render ui/ effects
-All state changes related to post page are handled with history change such that forward and backwards still works will filter

Database
- For simplicity of setup I used H2 in memory database, JPA will generate repository implementation for ease of usage

Notable Files:
|-- README.md: This file
|-- frontend: Frontend project
|   |-- index.html: Frontend entrypoint
|   |-- jest.config.js: Frontend test config
|   |-- src
|   |   |-- App.tsx: Actual App entry point
|   |   |-- ErrorHandler.tsx: Handles all received error, show them as prompt
|   |   |-- component
|   |   |   `-- Button.tsx: Wrapped Material UI Button, handles loading state disable Button to prevent double submits
|   |   |-- main.tsx : JS entry point, handles store/router handling
|   |   |-- mainSlice.ts: Main redux state, stores current user information and lastest error
|   |   |-- page: Main folder to contain different path/ features
|   |   |   |-- Post
|   |   |   |   |-- CreatePostDialog.tsx
|   |   |   |   |-- Table.tsx: Displays fetch data.
|   |   |   |   |-- index.tsx: Core driver, dispatches current history state to redux state
|   |   |   |   `-- postSlice.ts: Redux state
|   |   |   `-- Welcome
|   |   |       |-- Login.tsx
|   |   |       |-- Register.tsx
|   |   |       `-- index.tsx
|   |   |-- service: API helper, can be generated from swagger api
|   |   |   |-- PostService.ts
|   |   |   |-- ServerException.ts
|   |   |   `-- UserService.ts
|   |   |-- store.ts: Redux state
|   |-- test
|   |   `-- main.test.ts: Test for mainSlice for login/logout feature
`-- instapic: Backend project
    |-- build.gradle.kts - Dependencies, build setup
    `-- src
        |-- main
        |   |-- kotlin
        |   |   `-- com
        |   |       `-- jameswong
        |   |           `-- instapic
        |   |               |-- InstapicApplication.kt: Spring Boot entry point
        |   |               |-- file: Handles file storage
        |   |               |   |-- FileEntity.kt: The JPA entity we store in database
        |   |               |   |-- FileService.kt: FileService Interface
        |   |               |   |-- LocalFileController.kt: Serves user uploaded files
        |   |               |   |-- LocalFileRepository.kt: Spring JPA will generate the repository for this interface for CRUD
        |   |               |   |-- LocalFileResource.kt: Contain mime/filename in addition to spring resource file
        |   |               |   |-- LocalFileService.kt: Actual implementation of File service, stores the file in our system
        |   |               |   `-- LocalFileStorageProperties.kt: Loads local-file-service.uploadDirectory, use as the file directory
        |   |               |-- image
        |   |               |   `-- ImageService.kt: Ideally this is the only service that Post features need to use for storing image
        |   |               |-- post
        |   |               |   |-- Post.kt: JPA object
        |   |               |   |-- PostController.kt: Controller to CRUD of Post
        |   |               |   |-- PostRepository.kt: Spring JPA will generate the repository for this interface for CRUD
        |   |               |   |-- PostService.kt: Handles injection of current time and user
        |   |               |   `-- PostView.kt: The view object that is sent to user
        |   |               |-- security
        |   |               |   |-- LoginRequestHandler.kt: Handles RESTful login
        |   |               |   |-- SecurityConfig.kt: Security related config
        |   |               |   |-- UserDetailsImpl.kt: Override default UserDetails to use our User table
        |   |               |   `-- UserDetailsServiceImpl.kt: Override default UserDetails to use our User table
        |   |               `-- user
        |   |                   |-- User.kt: JPA object
        |   |                   |-- UserController.kt: User register, get-curren-user
        |   |                   |-- UserLoginRequest.kt: DTO for login request
        |   |                   |-- UserRepository.kt: Spring JPA will generate the repository for this interface for CRUD
        |   |                   |-- UserService.kt: Handles checking for duplicate usernames
        |   |                   `-- UsernameInUseException.kt
        |   `-- resources
        |       |-- META-INF
        |       |   `-- additional-spring-configuration-metadata.json
        |       |-- application.properties: Configuration
        |       |-- static: On production this is where the static file goes, that is the built project of frontend project
        `-- test
            `-- kotlin
                `-- com
                    `-- jameswong
                        `-- instapic
                            |-- InstapicApplicationTests.kt
                            |-- MockUserDetailsService.kt
                            |-- TestConfig.kt: Test Config to use MockUserDetailService during testing
                            |-- UserControllerTest.kt: test of valid input
                            `-- UserServiceTest.kt: test of no duplicate user
