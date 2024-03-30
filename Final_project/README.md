# Примеры запросов:

### Запрос на добавления пользователя
Адрес: http://localhost:8080/api/customers

Тип: POST

Тело: 
```json
{
  "name": "Ivanov Ivan",
  "birthDt": "2000-01-01"
}
```

### Запрос на получение всех пользователей
Адрес: http://localhost:8080/api/customers

Тип: GET

### Запрос на получение пользователя по id=0
Адрес: http://localhost:8080/api/customers/0

Тип: GET

### Запрос на изменения данных пользователя
Адрес: http://localhost:8080/api/customers

Тип: PUT

Тело:
```json
{
  "id": 0,
  "name": "Petrov Ivan",
  "birthDt": "2000-01-01"
}
```

### Запрос на удаление пользователя по id=0
Адрес: http://localhost:8080/api/customers/0

Тип: DELETE

### Запрос на добавление операции пользователю с id=0
Адрес: http://localhost:8080/api/operations/0

Тип: POST

Тело:
```json
{
  "type": "Bank transfer",
  "amount": 1000,
  "date": "2024-01-01"
}
```

### Запрос на показ всех операций пользователя с id=0
Адрес: http://localhost:8080/api/operations/0

Тип: GET

### Запрос на удаление операции id=2 у пользователя id=0
Адрес: http://localhost:8080/api/operations/0/2

Тип: DELETE