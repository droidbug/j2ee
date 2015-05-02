# j2ee -> smart-notes
+ Covered Topics
    - Jsp/Servlet.
    - Database Connection [ MySql ] (from .prpperties file).
    - Database Operation [ CRUD ].
    - Log4j (from .prpperties file).
    - Ajax.
    - REST api (Link).
    - http://localhost:8080/smart-notes/rest/note
    ```javascript
        Hello noteTest()
    ```
    
    - http://localhost:8080/smart-notes/rest/note/demoNote
    ```javascript
    {
        "id": 1,
        "description": null,
        "lastUpdateAt": null,
        "deleted": false,
        "title": "title-1",
        "importance": null,
        "creator": 0
    }
    ```
    
    - http://localhost:8080/smart-notes/rest/note/list
    ```javascript
    [
    {
        "id": 1,
        "description": "Description-2",
        "title": "title-1",
        "importance": "NORMAL",
        "creator": 1,
        "deleted": false,
        "lastUpdateAt": 1430485928000
    },
    {
        "id": 2,
        "description": "Description-22",
        "title": "title-12",
        "importance": "NORMAL",
        "creator": 1,
        "deleted": false,
        "lastUpdateAt": 1430486200000
    },
    {
        "id": 3,
        "description": "Description-2s2",
        "title": "title-1s2",
        "importance": "NORMAL",
        "creator": 1,
        "deleted": false,
        "lastUpdateAt": 1430486687000
    },
    {
        "id": 6,
        "description": "Description-34",
        "title": "title-23",
        "importance": "NORMAL",
        "creator": 1,
        "deleted": false,
        "lastUpdateAt": 1430487383000
    }
]
    ```
    
