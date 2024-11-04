~~1. Сделать подстановку времени на стороне сервера~~

~~2. Создать отдельный контроллер для проектов~~

~~3. Сделать страницу project-page.html по аналоги с timesheets.html~~

~~4. В timesheets-page.html в колонку "Проекты" добавить ссылку на проект~~

~~5. Необходимо в timesheet-page.dto добавить поле projectId(для создания ссылки)~~

~~6. В timesheets-page в колонку "проекты" сделать гиперссылку (по аналогии с колонкой "перейти")
файле readme, прописаны задание которые я выполнил (пункты 3,4,5,6).~~

~~7. Создать класс Employee (id, name, ...)
   7.1.Создать Controller,Service , Repository для сущности
   7.2.Создать ресурс /employee{id}/timesheet - получить все timesheet по сотруднику~~
~~8. Добавить в Timesheet поел employee типа Employee~~

~~9. Связываем Project <-> Employee отношением ManyToOne
   (То есть на проекте может быть несколько сотрудников ;
   один сотрудник может быть на нескольких проектах)~~

10. Передать строки RoleName в сущность Role
  10.1 Создать отдельную таблицу Role
  10.2 Связать User <->Role отношением ManyToMany
11. После 10 пункта подправить формирование полей в MyCustomUserDetailService
12. В SecurityFilterChain настроить :
  -Стандартная форма логина
  -Страницы с проектами доступны пользователям с ролью Admin
  -REST- ресурсы доступны пользователям с ролью rest
13. Для REST-ресурсов не показывать форму логина.
Т.е. если пользователь не авторизован, то его не редиректит на форму логина,
а сразу показывает 401. Для авторизации нужно отдельно получить JSESSIONID и подставить запрос 