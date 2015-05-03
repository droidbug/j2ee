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
    
    - http://localhost:8080/smart-notes/rest/note/create
    ```javascript
    - input
    {
        "title": "title-23",
        "description": "Description-34",
        "importance": "NORMAL",
        "creator": 1
    }
    - output
    {
        "id": 6,
        "description": "Description-34",
        "title": "title-23",
        "importance": "NORMAL",
        "creator": 1,
        "lastUpdateAt": 1430487383000,
        "deleted": false
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
    
    - http://localhost:8080/smart-notes/rest/note/update/6
    ```javascript
    - input
    {
        "description": "XDescription-34",
        "title": "Xtitle-23"
    }
    - output
    {
        "id": 6,
        "description": "XDescription-34",
        "deleted": false,
        "lastUpdateAt": 1430487383000,
        "title": "Xtitle-23",
        "importance": "NORMAL",
        "creator": 1
    }
    ```
    
    - http://localhost:8080/smart-notes/rest/note/delete/5
    ```javascript
    {
        "statusCode": "DB_DELETE_OK",
        "message": "Note deletion successfull had id 5"
    }
    ```
    
