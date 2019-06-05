package idu.cs.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import idu.cs.entity.UserEntity;
@Repository
public interface UserRepository 
	extends JpaRepository<UserEntity, Long> {
	//findById, save, delete선언없어도 구현 가능
	
	//아래 메소드들은 선언해야 JPA 규칙에 의해 구햔됨
	//find - select문 , By - where, Order By, ASC와 Desc를 함께 사용 가능
	List<UserEntity> findByName(String name);
	//id 내림 차순으로 정렬
	//List<UserEntity> findByNameOrederByIdDESC(String name);
	List<UserEntity> findByCompany(String company);
	
	UserEntity findByUserId(String userId); //ByUserId == where userid=''
}
