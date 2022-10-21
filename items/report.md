# Purple Switch Report

## Table of Contents

1. [Team Members and Roles](#team-members-and-roles)
2. [Summary of Individual Contributions](#summary-of-individual-contributions)
3. [Conflict Resolution Protocol](#conflict-resolution-protocol)
4. [Application Description](#application-description)
5. [Application UML](#application-uml)
6. [Application Design and Decisions](#application-design-and-decisions)
7. [Summary of Known Errors and Bugs](#summary-of-known-errors-and-bugs)
8. [Testing Summary](#testing-summary)
9. [Implemented Features](#implemented-features)
10. [Team Meetings](#team-meetings)

## Team Members and Roles

| UID | Name | Role |
| :--- | :----: | ---: |
| U7366711 | Yuxuan Zhao | UX & UI designer, software developer, software tester |
| U7326123 | (Rita) Xinyun Zhou | UX & UI designer, software developer, software tester, data manager |
| U6659041 | Fiona Chu | Product owner, meeting minutes scribe |
| U7577606 | Lachlan Scott | Product owner |

## Summary of Individual Contributions

U7366711, Yuxuan Zhao, I contribute % of the project. Here are my contributions:
-	(UI design) Introduce Material Design 3
-	(UI design) [activity_main.xml](https://gitlab.cecs.anu.edu.au/u7577606/ga-22s2-comp2100-6442/-/blob/main/app/src/main/res/layout/activity_main.xml)
-	(UI design) [bottom_navigation_menu.xml](https://gitlab.cecs.anu.edu.au/u7577606/ga-22s2-comp2100-6442/-/blob/main/app/src/main/res/menu/bottom_navigation_menu.xml)
-	(UI design) [fragment_home.xml](https://gitlab.cecs.anu.edu.au/u7577606/ga-22s2-comp2100-6442/-/blob/main/app/src/main/res/layout/fragment_home.xml)
-	(UI design) [fragment_item_list.xml](https://gitlab.cecs.anu.edu.au/u7577606/ga-22s2-comp2100-6442/-/blob/main/app/src/main/res/layout/fragment_item_list.xml)
-	(UI design) [fragment_item_detail.xml](https://gitlab.cecs.anu.edu.au/u7577606/ga-22s2-comp2100-6442/-/blob/main/app/src/main/res/layout/fragment_item_detail.xml)
-	(UI design) [fragment_login.xml](https://gitlab.cecs.anu.edu.au/u7577606/ga-22s2-comp2100-6442/-/blob/main/app/src/main/res/layout/fragment_login.xml)
-	(UI design) [fragment_order_list.xml](https://gitlab.cecs.anu.edu.au/u7577606/ga-22s2-comp2100-6442/-/blob/main/app/src/main/res/layout/fragment_order_list.xml)
-	(UI design) [fragment_person.xml (L6-52)](https://gitlab.cecs.anu.edu.au/u7577606/ga-22s2-comp2100-6442/-/blob/main/app/src/main/res/layout/fragment_person.xml#L6-52)
-	(UI design) [recycler_view_row.xml (L7-56)](https://gitlab.cecs.anu.edu.au/u7577606/ga-22s2-comp2100-6442/-/blob/main/app/src/main/res/layout/recycler_view_row.xml#L7-56)
-	(UI design) [recycler_view_row_order.xml](https://gitlab.cecs.anu.edu.au/u7577606/ga-22s2-comp2100-6442/-/blob/main/app/src/main/res/layout/recycler_view_row_order.xml)
-	(Data) [Icons and avatars](https://gitlab.cecs.anu.edu.au/u7577606/ga-22s2-comp2100-6442/-/tree/main/app/src/main/res/drawable)
-	(Code design) Design patterns: Singleton, State, DAO
-	(Code design) Data structure: AVL Tree
-	(Code implementation) [Read data instances from local files](https://gitlab.cecs.anu.edu.au/u7577606/ga-22s2-comp2100-6442/-/blob/main/app/src/main/java/com/example/mymarketplaceapp/models/UserDao.java#L54-72)
-	(Code implementation) [Item](https://gitlab.cecs.anu.edu.au/u7577606/ga-22s2-comp2100-6442/-/blob/main/app/src/main/java/com/example/mymarketplaceapp/models/Item.java)
-	(Code implementation) [ItemDao](https://gitlab.cecs.anu.edu.au/u7577606/ga-22s2-comp2100-6442/-/blob/main/app/src/main/java/com/example/mymarketplaceapp/models/ItemDao.java)
-	(Code implementation) [User](https://gitlab.cecs.anu.edu.au/u7577606/ga-22s2-comp2100-6442/-/blob/main/app/src/main/java/com/example/mymarketplaceapp/models/User.java)
-	(Code implementation) [UserDao](https://gitlab.cecs.anu.edu.au/u7577606/ga-22s2-comp2100-6442/-/blob/main/app/src/main/java/com/example/mymarketplaceapp/models/UserDao.java)
-	(Code implementation) [UserState](https://gitlab.cecs.anu.edu.au/u7577606/ga-22s2-comp2100-6442/-/blob/main/app/src/main/java/com/example/mymarketplaceapp/models/UserState.java)
-	(Code implementation) [SessionState](https://gitlab.cecs.anu.edu.au/u7577606/ga-22s2-comp2100-6442/-/blob/main/app/src/main/java/com/example/mymarketplaceapp/models/SessionState.java)
-	(Code implementation) [NoSessionState](https://gitlab.cecs.anu.edu.au/u7577606/ga-22s2-comp2100-6442/-/blob/main/app/src/main/java/com/example/mymarketplaceapp/models/NoSessionState.java)
-	(Code implementation) [UserSession](https://gitlab.cecs.anu.edu.au/u7577606/ga-22s2-comp2100-6442/-/blob/main/app/src/main/java/com/example/mymarketplaceapp/models/UserSession.java)
-	(Code implementation) [Order](https://gitlab.cecs.anu.edu.au/u7577606/ga-22s2-comp2100-6442/-/blob/main/app/src/main/java/com/example/mymarketplaceapp/models/Order.java)
-	(Code implementation) [OrderManager](https://gitlab.cecs.anu.edu.au/u7577606/ga-22s2-comp2100-6442/-/blob/main/app/src/main/java/com/example/mymarketplaceapp/models/OrderManager.java)
-	(Code implementation) [AVLTree (based on lab code)](https://gitlab.cecs.anu.edu.au/u7577606/ga-22s2-comp2100-6442/-/blob/main/app/src/main/java/com/example/mymarketplaceapp/utils/AVLTree.java)
-	(Code implementation) [Token](https://gitlab.cecs.anu.edu.au/u7577606/ga-22s2-comp2100-6442/-/blob/main/app/src/main/java/com/example/mymarketplaceapp/utils/Token.java)
-	(Code implementation) [Tokenizer](https://gitlab.cecs.anu.edu.au/u7577606/ga-22s2-comp2100-6442/-/blob/main/app/src/main/java/com/example/mymarketplaceapp/utils/Tokenizer.java)
-	(Code implementation) [Parser](https://gitlab.cecs.anu.edu.au/u7577606/ga-22s2-comp2100-6442/-/blob/main/app/src/main/java/com/example/mymarketplaceapp/utils/Parser.java)
-	(Code implementation) [Query](https://gitlab.cecs.anu.edu.au/u7577606/ga-22s2-comp2100-6442/-/blob/main/app/src/main/java/com/example/mymarketplaceapp/utils/Query.java)
-	(Code implementation) [ContextUtil](https://gitlab.cecs.anu.edu.au/u7577606/ga-22s2-comp2100-6442/-/blob/main/app/src/main/java/com/example/mymarketplaceapp/utils/ContextUtil.java)
-	(Code implementation) [RecyclerViewAdapter (L31-79)](https://gitlab.cecs.anu.edu.au/u7577606/ga-22s2-comp2100-6442/-/blob/main/app/src/main/java/com/example/mymarketplaceapp/adapters/RecyclerViewAdapter.java#L31-79)
-	(Code implementation) [OrderRecyclerViewAdapter](https://gitlab.cecs.anu.edu.au/u7577606/ga-22s2-comp2100-6442/-/blob/main/app/src/main/java/com/example/mymarketplaceapp/adapters/OrderRecyclerViewAdapter.java)
-	(Code implementation) [HomeFragment](https://gitlab.cecs.anu.edu.au/u7577606/ga-22s2-comp2100-6442/-/blob/main/app/src/main/java/com/example/mymarketplaceapp/fragments/HomeFragment.java)
-	(Code implementation) [ItemListFragment (L35-86)](https://gitlab.cecs.anu.edu.au/u7577606/ga-22s2-comp2100-6442/-/blob/main/app/src/main/java/com/example/mymarketplaceapp/fragments/ItemListFragment.java#L35-86)
-	(Code implementation) [ItemDetailFragment (L32-76)](https://gitlab.cecs.anu.edu.au/u7577606/ga-22s2-comp2100-6442/-/blob/main/app/src/main/java/com/example/mymarketplaceapp/fragments/ItemDetailFragment.java#L32-76)
-	(Code implementation) [LoginFragement](https://gitlab.cecs.anu.edu.au/u7577606/ga-22s2-comp2100-6442/-/blob/main/app/src/main/java/com/example/mymarketplaceapp/fragments/LoginFragment.java)
-	(Code implementation) [OrderListFragment](https://gitlab.cecs.anu.edu.au/u7577606/ga-22s2-comp2100-6442/-/blob/main/app/src/main/java/com/example/mymarketplaceapp/fragments/OrderListFragment.java)
-	(Code implementation) [PersonFragment (L26-54)](https://gitlab.cecs.anu.edu.au/u7577606/ga-22s2-comp2100-6442/-/blob/main/app/src/main/java/com/example/mymarketplaceapp/fragments/PersonFragment.java#L26-54)
-	(Code implementation) [ParserTest](https://gitlab.cecs.anu.edu.au/u7577606/ga-22s2-comp2100-6442/-/blob/main/app/src/test/java/com/example/mymarketplaceapp/utils/ParserTest.java)
-	(Code implementation) [TokenizerTest](https://gitlab.cecs.anu.edu.au/u7577606/ga-22s2-comp2100-6442/-/blob/main/app/src/test/java/com/example/mymarketplaceapp/utils/TokenizerTest.java)
-	(Items) [protocol.md (organized into .md)](https://gitlab.cecs.anu.edu.au/u7577606/ga-22s2-comp2100-6442/-/blob/main/items/protocol.md)
-	(Items) [plan.md (organized into .md)](https://gitlab.cecs.anu.edu.au/u7577606/ga-22s2-comp2100-6442/-/blob/main/items/plan.md)
-	(Others) Determine directory structure and naming convention
-	(Others) Attend every meeting
-	(Report writing)
-	(Slide presentation)


## Conflict Resolution Protocol

[Conflict Resolution Protocol](https://gitlab.cecs.anu.edu.au/u7577606/ga-22s2-comp2100-6442/-/blob/main/items/protocol.md)

## Application Description

*[What is your application, what does it do? Include photos or diagrams if necessary]*

*Here is a pet specific application example*

*PetBook is a social media application specifically targetting pet owners... it provides... certified practitioners, such as veterians are indicated by a label next to their profile...*

**Application Use Cases and or Examples**

*[Provide use cases and examples of people using your application. Who are the target users of your application? How do the users use your application?]*

*Here is a pet training application example*

*Molly wants to inquiry about her cat, McPurr's recent troublesome behaviour*
1. *Molly notices that McPurr has been hostile since...*
2. *She makes a post about... with the tag...*
3. *Lachlan, a vet, writes a reply to Molly's post...*
4. ...
5. *Molly gives Lachlan's reply a 'tick' response*

*Here is a map navigation application example*

*Targets Users: Drivers*

* *Users can use it to navigate in order to reach the destinations.*
* *Users can learn the traffic conditions*
* ...

*Target Users: Those who want to find some good restaurants*

* *Users can find nearby restaurants and the application can give recommendations*
* ...

*List all the use cases in text descriptions or create use case diagrams. Please refer to https://www.visual-paradigm.com/guide/uml-unified-modeling-language/what-is-use-case-diagram/ for use case diagram.*

## Application UML

![UML](./images/uml.png) <br>

[UML Class Diagram @smartdraw](https://cloud.smartdraw.com/share.aspx/?pubDocShare=4D5A08F649B156CCAA852F5A988AD8839F3)

## Application Design and Decisions

**Data Structures**

1. ArrayList
   - Objective: It is used for storing small, infrequently accessed data.
   - Location: [Cart](https://gitlab.cecs.anu.edu.au/u7577606/ga-22s2-comp2100-6442/-/blob/main/app/src/main/java/com/example/mymarketplaceapp/models/Cart.java#L15),  [Query](https://gitlab.cecs.anu.edu.au/u7577606/ga-22s2-comp2100-6442/-/blob/main/app/src/main/java/com/example/mymarketplaceapp/utils/Query.java#L30) etc.
   - Reasons:
      - ArrayList is easy to use and reliable
      - In these features, we do not need to search or sort elements

2. AVL Tree
   - Objective: It is used for storing items and users.
   - Location: [UserDAO](https://gitlab.cecs.anu.edu.au/u7577606/ga-22s2-comp2100-6442/-/blob/main/app/src/main/java/com/example/mymarketplaceapp/models/UserDao.java#L79-123),  [ItemDAO](https://gitlab.cecs.anu.edu.au/u7577606/ga-22s2-comp2100-6442/-/blob/main/app/src/main/java/com/example/mymarketplaceapp/models/ItemDao.java#L77-104)
   - Reasons:
      - We need to search and sort users and products frequently, and AVL Tree is more efficient than Arraylist
      - Mobile apps are unlikely to run on hard disk drive, so B-tree has no advantage over AVL tree here
      - We do not implement the feature of deleting items and users, so Red-Black Tree has little advantage over AVL tree here


**Design Patterns**

1. DAO (Data Access Object)
   - Objective: It is used to read data without exposing details of the data storage (`json` or `Firebase`).
   - Location: [UserDAO](https://gitlab.cecs.anu.edu.au/u7577606/ga-22s2-comp2100-6442/-/blob/main/app/src/main/java/com/example/mymarketplaceapp/models/UserDao.java),  [ItemDAO](https://gitlab.cecs.anu.edu.au/u7577606/ga-22s2-comp2100-6442/-/blob/main/app/src/main/java/com/example/mymarketplaceapp/models/ItemDao.java)
   - Reasons:
      - We started by reading data from json, and considering that we would have to implement reading data from Firebase later
      - We need to decouple the front end from the data source

2. State
   - Objective: It is used for defining the different state of user, and user will have different action in different state .
   - Location: [UserSession](https://gitlab.cecs.anu.edu.au/u7577606/ga-22s2-comp2100-6442/-/blob/main/app/src/main/java/com/example/mymarketplaceapp/models/UserSession.java), [UserState](https://gitlab.cecs.anu.edu.au/u7577606/ga-22s2-comp2100-6442/-/blob/main/app/src/main/java/com/example/mymarketplaceapp/models/UserState.java), [SessionState](https://gitlab.cecs.anu.edu.au/u7577606/ga-22s2-comp2100-6442/-/blob/main/app/src/main/java/com/example/mymarketplaceapp/models/SessionState.java), [NoSessionState](https://gitlab.cecs.anu.edu.au/u7577606/ga-22s2-comp2100-6442/-/blob/main/app/src/main/java/com/example/mymarketplaceapp/models/NoSessionState.java)
   - Reasons:
      - Using State design pattern, rather than conditional statements, improves the readability of the code
      - Facilitate the subsequent addition of more account statuses (e.g. `banned` for suspected fraud)

3. Singleton
   - Objective: It is used to ensure that a class (e.g. `OrderManager`) has just a single instance.
   - Location: [OrderManager](https://gitlab.cecs.anu.edu.au/u7577606/ga-22s2-comp2100-6442/-/blob/main/app/src/main/java/com/example/mymarketplaceapp/models/OrderManager.java), [UserDAO](https://gitlab.cecs.anu.edu.au/u7577606/ga-22s2-comp2100-6442/-/blob/main/app/src/main/java/com/example/mymarketplaceapp/models/UserDao.java),  [ItemDAO](https://gitlab.cecs.anu.edu.au/u7577606/ga-22s2-comp2100-6442/-/blob/main/app/src/main/java/com/example/mymarketplaceapp/models/ItemDao.java)
   - Reasons:
      - To control access to the shared resources (`item.json`,`user.json` and `Firebase` )
      - Provide a global access point to the `OrderManager` and DAOs

**Grammar(s)**

Production Rules:
    
    <query>    ::= <item name> | <item name> <filter>
    <filter>   ::= <criteria> | <criteria> <filter>
    <criteria> ::= <category> | <user name> | <minimum price> | <maximum price> | <description>
    
Unlike mathematical expressions, the search queries in shopping apps do not require the use of binary operator. Users should be allowed to simply indicate different filter criteria using different symbols. The grammar and symbols should be as intuitive as possible to the users.

Location: [Parser](https://gitlab.cecs.anu.edu.au/u7577606/ga-22s2-comp2100-6442/-/blob/main/app/src/main/java/com/example/mymarketplaceapp/utils/Parser.java)

Example:
- Original query:`uPhone #ELECTRONICS @JB Wi-Fi >1024 <2048 ^bullet-proof`
- Meaning: Search in `ELECTRONICS` category for items with `uPhone` in the item name of which the seller is named `JB Wi-Fi`, the price is between `1024` and `2048`, and the description includes `bullet-proof`

Advantages:
- The grammar can satisfy most of the users' search needs
- The grammar is very intuitive
- It is convenient to design a parser for such a grammar
- The grammar makes it easier to handle illegal input

**Tokenizer and Parsers**

Location: [Parser](https://gitlab.cecs.anu.edu.au/u7577606/ga-22s2-comp2100-6442/-/blob/main/app/src/main/java/com/example/mymarketplaceapp/utils/Parser.java), [Tokenizer](https://gitlab.cecs.anu.edu.au/u7577606/ga-22s2-comp2100-6442/-/blob/main/app/src/main/java/com/example/mymarketplaceapp/utils/Tokenizer.java), [Token](https://gitlab.cecs.anu.edu.au/u7577606/ga-22s2-comp2100-6442/-/blob/main/app/src/main/java/com/example/mymarketplaceapp/utils/Token.java)

We use `Parser` to parse the text in search box into `Query`. A successfully parsed `Query` instance consists of a `String itemName` (mandatory) and a `List<Token> filter` (optional). A `filter` consists of several criteria (`Token`). A `Token` instance consists of `String token` and `Type type`.

|Token.Type|Symbol|
-|-
ITEM_NAME|
CATEGORY|#
USERNAME|@
MIN_PRICE|>
MAX_PRICE|<
DESCRIPTION|^

Advantages:
- Token identifiers are very intuitive to the user and at the same time rarely used in item names and descriptions
- Parser and Tokenizer are both highly readable, maintainable and extensible

**Other**

*[What other design decisions have you made which you feel are relevant? Feel free to separate these into their own subheadings.]*

## Summary of Known Errors and Bugs

*[Where are the known errors and bugs? What consequences might they lead to?]*

*Here is an example:*

1. *Bug 1:*

- *A space bar (' ') in the sign in email will crash the application.*
- ...

2. *Bug 2:*
3. ...

*List all the known errors and bugs here. If we find bugs/errors that your team does not know of, it shows that your testing is not thorough.*

## Testing Summary

*[What features have you tested? What is your testing coverage?]*

*Here is an example:*

- *Number of test cases: ...*

- *Code coverage: ...*

- *Types of tests created: ...*

*Please provide some screenshots of your testing summary, showing the achieved testing coverage. Feel free to provide further details on your tests.*

## Implemented Features

*[What features have you implemented?]*

*Here are some examples:*

Feature Category: Privacy <br>
*Implemented features:*
1. Feature 1: **Users may ... . (easy)**
   * Class X, methods Z, Y, Lines of code: 10-100
   * Class Y, methods K, L, M, Lines of code: 35-150
   * Your description: ...
<br>
2. Feature 2: **A user can only ... . (medium)**
<br>

Feature Category: Firebase Integration <br>
*Implemented features:* <br>
1. **Use Firebase to implement user Authentication/Authorisation. (easy)**
   * Class A: methods A, B, C, lines of code: whole file
   * …

*List all features you have completed in their separate categories with their difficulty classification. If they are features that are suggested and approved, please state this somewhere as well.*

## Team Meetings

*[Team Meeting 1](./06_09_2022_Meeting_Minutes.md)*

*[Team Meeting 2](./15_10_2022_Meeting_Minutes.md)*

*[Team Meeting 3](./17_10_2022_Meeting_Minutes.md)*

*[Team Meeting 4](./19_10_2022_Meeting_Minutes.md)*

*[Team Meeting 5](./20_10_2022_Meeting_Minutes.md)*
