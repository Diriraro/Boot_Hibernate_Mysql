package com.iu.s1.member;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;

@Data
@Entity
@Table(name = "member")
@DynamicUpdate
public class MemberVO {
	
	@Id//primary key
	@NotEmpty
	private String id;
	@Column //일반 컬럼명
//	@NotEmpty
//	@Size(min = 2, max = 13)
	private String pw;
	@Transient //테이블에서 제외(테이블 만들때 들어갈 컬럼명이 아니다 라고 알려줌)
	private String pwCheck;
	@Column
//	@NotEmpty
	private String name;
	@Column
//	@NotEmpty
	@Email
	private String email;
	@Column
//	@NotEmpty
	private String phone;
	
	//mappedBy = "Join 하는 Entity에 선언된 자기 자신의 Entity 변수명"
	@OneToOne(mappedBy = "memberVO", cascade = CascadeType.ALL)
	private MemberFileVO memberFileVO;
	
}
