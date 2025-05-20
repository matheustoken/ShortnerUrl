# 🔗 URL Shortener Service

This project is a simple URL shortening service that allows users to convert long URLs into compact, shareable links.

---

## 📌 Example

### Request

```http
POST /shorten-url
Content-Type: application/json

{
    "url": "https://backendbrasil.com.br"
}
```

### Response

```http
HTTP/1.1 200 OK
Content-Type: application/json

{
    "url": "https://xxx.com/DXB6V"
}
```

---

## ✅ Features

- Accepts a long URL as input and returns a shortened version.
- Shortened URLs consist of **5 to 10 alphanumeric characters**.
- Only **letters and numbers** are allowed in the shortened code.
- Shortened URLs are **stored in a database** with an **expiration time**.
- Redirects to the original URL when the shortened link is accessed.
- Returns **HTTP 404 Not Found** if the shortened URL does not exist or has expired.

---

## ⚙️ Requirements

- Java (Spring Boot)
- PostgreSQL (or another database of your choice)
- Maven or Gradle
