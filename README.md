# RDBMS 선택 과정
**ROWS:** 8,644

# 날짜별 성능 비교
## 1. 조회 성능 비교
> **API:** /api/v1/weathers/details?day=20101030

|                |  시간  |  시간  |  시간  |  시간  |  시간  |
|:--------------:|:----:|:----:|:----:|:----:|:----:|
| **PostgreSQL** | 6 ms | 4 ms | 4 ms | 5 ms | 6 ms |
|   **MySQL**    | 8 ms | 7 ms | 7 ms | 8 ms | 7 ms |

- **PostgreSQL:** 약 5 ms
- **MySQL:** 약 7.4 ms

### 결론
- 날짜별 조회 성능에서는 PostgreSQL이 MySQL에 비해 약 32% 더 빨랐습니다.



## 2. 인덱싱을 적용한 조회 성능 비교
> **API:** /api/v1/weathers/details?day=20101030

|                |  시간  |  시간  |  시간  |  시간  |  시간  |
|:--------------:|:----:|:----:|:----:|:----:|:----:|
| **PostgreSQL** | 4 ms | 4 ms | 4 ms | 5 ms | 3 ms |
|   **MySQL**    | 5 ms | 5 ms | 5 ms | 6 ms | 4 ms |

- **PostgreSQL:** 약 4 ms
- **MySQL:** 약 5 ms

### 결론
- PostgreSQL의 조회 성능은 인덱싱 전후 비교시 약 20% 개선되었습니다.
- MySQL의 조회 성능은 인덱싱 전후 비교시 약 32% 개선되었습니다.
- 인덱싱을 적용한 후의 날짜별 조회 성능에서 PostgreSQL이 MySQL보다 약 20% 더 빨랐습니다.



## 3. 캐싱을 적용한 조회 성능 비교
> **API:** /api/v1/weathers/details?day=20101030

|                |  시간   |  시간  |  시간  |  시간  |  시간  |
|:--------------:|:-----:|:----:|:----:|:----:|:----:|
| **PostgreSQL** | 14 ms | 3 ms | 3 ms | 2 ms | 3 ms |
|   **MySQL**    | 13 ms | 3 ms | 2 ms | 2 ms | 2 ms |

- **PostgreSQL:** 약 5 ms(일시적 증가), 최종 3 ms
- **MySQL:** 약 4.4 ms(일시적 증가), 최종 2.4 ms

## 결론
- 캐싱 초기에는 성능이 잠시 감소되지만, 캐싱 적용 후 최종적으로 성능이 약 33% 에서 85% 정도 향상되었습니다.
- 최종적으로 PostgreSQL과 MySQL 간의 성능 차이는 미미해졌습니다.



## 4. 캐시 저장을 비동기로 처리했을 때 성능 비교
> **API:** /api/v1/weathers/details?day=20101030

|                |  시간  |  시간  |  시간  |  시간  |  시간  |
|:--------------:|:----:|:----:|:----:|:----:|:----:|
| **PostgreSQL** | 8 ms | 2 ms | 3 ms | 2 ms | 2 ms |
|   **MySQL**    | 8 ms | 2 ms | 2 ms | 2 ms | 4 ms |

- **PostgreSQL:** 약 3.4 ms, (비동기 처리 후 2 ms)
- **MySQL:** 약 3.6 ms, (비동기 처리 후 2.4 ms)

## 결론
- 비동기 캐싱 적용 후 PostgreSQL의 성능은 약 50% 향상되었습니다.
- 비동기 캐싱 적용 후 MySQL의 성능은 약 55% 향상되었습니다.

---

# 날짜 범위별 성능 비교

## 1. 조회 성능 비교
> **API:** /api/v1/weathers?start=1514764800&end=1552694400

|                |  시간   |  시간   |  시간   |  시간   |  시간   |
|:--------------:|:-----:|:-----:|:-----:|:-----:|:-----:|
| **PostgreSQL** | 21 ms | 14 ms | 18 ms | 26 ms | 23 ms |
|   **MySQL**    | 33 ms | 37 ms | 24 ms | 36 ms | 32 ms |

- **PostgreSQL:** 약 20.4 ms
- **MySQL:** 약 32.4 ms

### 결론
- 날짜 범위별 조회 성능에서는 PostgreSQL이 MySQL에 비해 약 37% 더 빨랐습니다.



## 2. 인덱싱을 적용한 조회 성능 비교
> **API:** /api/v1/weathers?start=1514764800&end=1552694400

|                |  시간   |  시간   |  시간   |  시간   |  시간   |
|:--------------:|:-----:|:-----:|:-----:|:-----:|:-----:|
| **PostgreSQL** | 17 ms | 15 ms | 21 ms | 29 ms | 12 ms |
|   **MySQL**    | 19 ms | 11 ms | 17 ms | 13 ms | 13 ms |

- **PostgreSQL:** 약 18.8 ms
- **MySQL:** 약 14.6 ms

### 결론
- PostgreSQL의 조회 성능은 인덱싱 전후 비교시 약 7.84% 개선되었습니다.
- MySQL의 조회 성능은 인덱싱 전후 비교시 약 54.94% 개선되었습니다.
- 인덱싱을 적용한 후의 날짜 범위별 조회 성능에서는 MySQL이 PostgreSQL에 비해 약 22% 더 빨랐습니다.



## 종합 결론
### **PostgreSQL:**
1. 날짜별 조회시 기본 조회 성능과 비교했을 때 비동기 캐싱 적용 후 성능이 약 60% 향상되었습니다.
2. 날짜 범위별 조회시 기본 조회 성능과 비교했을 때 인덱스를 적용 후 성능이 약 7% 향상되었습니다.

### **MySQL:**
1. 날짜별 조회시 기본 조회 성능과 비교했을 때 비동기 캐싱 적용 후 성능이 약 68% 향상되었습니다.
2. 날짜 범위별 조회시 기본 조회 성능과 비교했을 때 인덱스를 적용 후 성능이 약 54% 향상되었습니다.

### MySQL과 PostgreSQL의 비교
1. 결과적으로 날짜별 조회시 조회 성능은 MySQL과 PostgreSQL은 성능 차이가 미미해졌습니다.
2. 결과적으로 날짜 범위별 조회시 조회 성능은 MySQL이 PostgreSQL보다 22% 더 빨랐습니다.

해당 분석을 통해 성능적으로 MySQL이 해당 서비스에 있어 더 적합한 선택으로 보입니다.