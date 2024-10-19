# Smart Table

## Описание

Данный проект представляет собой API на основе Spring Boot, который предоставляет функциональность для управления пользователями и проектами. API позволяет создавать, удалять и получать пользователей и проекты.

## Технологии

- Spring Boot
- Spring Data JPA
- Docker
- PostgreSQL (или другой используемый драйвер базы данных)

## Развертывание проекта

### Предварительные требования

- Установленный Docker
- Установленный Docker Compose

### Шаги по развертыванию

1. **Клонируйте репозиторий:**
   ```bash
   git clone <URL вашего репозитория>
   cd <название_папки_репозитория>
2. **Соберите и запустите контейнеры:**

    ```bash
    docker-compose up --build

## Доступные API запросы

### 1. Управление пользователями

#### Создание пользователя

- **Метод**: POST
- **URL**: `/admin/users`

##### Тело запроса:
   ```json
   {
      "name": "Имя пользователя",
      "role": "Роль пользователя",
      "email": "email@example.com"
   }
```
##### Ответ:
- **Статус**: 201 Created
- **Тело ответа**:
```json
{
  "id": 1,
  "name": "Имя пользователя",
  "role": "Роль пользователя",
  "email": "email@example.com"
}
```
### Удаление пользователя

- **Метод**: DELETE
- **URL**: `/admin/users/{userId}`
- **Параметры**:
   - `userId` — ID пользователя для удаления.

##### Ответ:
- **Статус**: 204 No Content

### Получение списка пользователей

- **Метод**: GET
- **URL**: `/admin/users`

##### Ответ:
- **Статус**: 200 OK
- **Тело ответа**:
```json
[
   {
      "id": 1,
      "name": "Имя пользователя 1",
      "role": "Роль пользователя 1",
      "email": "email1@example.com"
   },
   {
      "id": 2,
      "name": "Имя пользователя 2",
      "role": "Роль пользователя 2",
      "email": "email2@example.com"
   }
]
```
### Управление проектами

#### Получение всех проектов

- **Метод**: GET
- **URL**: `/projects`
- **Параметры**:
   - `resources` — (необязательный) Набор ID ресурсов.
   - `managers` — (необязательный) Набор ID менеджеров.
   - `sort` — (необязательный) Поле для сортировки (id, name, lastUpdate). По умолчанию: id.
   - `size` — (необязательный) Количество проектов на странице. По умолчанию: 10.

##### Ответ:
- **Статус**: 200 OK
- **Тело ответа**:
```json
[
  {
    "id": 1,
    "pmId": 1,
    "projectName": "Название проекта",
    "status": "Статус проекта",
    "estimation": 1000,
    "lastUpdate": "2024-01-01T12:00:00",
    "projectStart": "2024-01-01T12:00:00",
    "projectEnd": "2024-12-31T12:00:00",
    "resources": []
  }
]
```
### Создание нового проекта

- **Метод**: POST
- **URL**: `/projects`

##### Тело запроса:
```json
{
  "pmId": 1,
  "projectName": "Название нового проекта",
  "status": "Статус проекта",
  "resources": [],
  "estimate": 1000,
  "projectStart": "2024-01-01T12:00:00",
  "projectEnd": "2024-12-31T12:00:00"
}
```
##### Ответ:
- **Статус**: 201 Created
- **Тело ответа**:
```json
{
  "id": 1,
  "pmId": 1,
  "projectName": "Название нового проекта",
  "status": "Статус проекта",
  "estimation": 1000,
  "lastUpdate": "2024-01-01T12:00:00",
  "projectStart": "2024-01-01T12:00:00",
  "projectEnd": "2024-12-31T12:00:00",
  "resources": []
}
```
### Удаление проекта

- **Метод**: DELETE
- **URL**: `/projects`
- **Параметры**:
    - `projectId` — ID проекта для удаления.

##### Ответ:
- **Статус**: 204 No Content

### Поиск проектов

- **Метод**: GET
- **URL**: `/projects/search`
- **Параметры**:
    - `query` — строка для поиска в названии проекта.

##### Ответ:
- **Статус**: 200 OK
- **Тело ответа**:
```json
[
  {
    "id": 1,
    "pmId": 1,
    "projectName": "Название проекта",
    "status": "Статус проекта",
    "estimation": 1000,
    "lastUpdate": "2024-01-01T12:00:00",
    "projectStart": "2024-01-01T12:00:00",
    "projectEnd": "2024-12-31T12:00:00",
    "resources": []
  }
]