# j2ee -> smart-notes
+ Covered Topics
    - Jsp/Servlet.
    - Database Connection [ MySql ] (from .prpperties file).
    - Database Operation [ CRUD ].
    - Log4j (from .prpperties file).
    - Ajax.
    - REST api (Link).
    - http://localhost:8080/smart-notes/rest/note
    /*
        Hello noteTest()
    */
    - http://localhost:8080/smart-notes/rest/note/noteTest
    /*
    {
        "id": 1,
        "description": null,
        "lastUpdateAt": null,
        "deleted": false,
        "title": "title-1",
        "importance": null,
        "creator": 0
    }
    */
    - http://localhost:8080/smart-notes/rest/note/all
    /*
    [
        {
            "id": 1,
            "description": "Description-2",
            "lastUpdateAt": 1430321976129,
            "deleted": false,
            "title": "title-1",
            "importance": null,
            "creator": 0
        },
        {
            "id": 2,
            "description": "Description-4",
            "lastUpdateAt": 1430321976129,
            "deleted": false,
            "title": "title-2",
            "importance": null,
            "creator": 0
        },
        {
            "id": 3,
            "description": "Description-6",
            "lastUpdateAt": 1430321976129,
            "deleted": false,
            "title": "title-3",
            "importance": null,
            "creator": 0
        }
    ]
    */