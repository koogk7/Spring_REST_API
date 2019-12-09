## Lombok
> target 패키지에서 롬복이 적용된 코드를 확인 할 수 있음

### EqualsAndHashCode
- EqualsAndHashCode객체 간의 연관관계가 있을 때 상호참조과 있게되면 스택오버플로우가 발생 할 수 있다
- 때문에 EqualsAndHashCode Of로 연관관계가 있는 필드를 주는건 바람직하지 않음는 기본적으로 모든 필드를 이용해서 비교
- 롬북 어노테이션은 스프링 메타 어노테이션으로 동작하지 않기 때문에 이 어노테이션의 중복을 줄일 수 없음 ㅠ
- 롬북에서는 @Data를 제공하는데, EqualsAndHashCode를 모든 필드를 사용해서 구현하기 때문에 권장하지 않음
