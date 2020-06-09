package com.iu.s1.member;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name = "member")
public class MemberVO {
	
	@Id//primary key
	@NotEmpty
	private String id;
	@Column //일반 컬럼명
	@NotEmpty
	@Size(min = 4, max = 13)
	private String pw;
	@Transient //테이블에서 제외(테이블 만들때 들어갈 컬럼명이 아니다 라고 알려줌)
	private String pwCheck;
	@Column
	@NotEmpty
	private String name;
	@Column
	@NotEmpty
	@Email
	private String email;
	@Column
	@NotEmpty
	private String phone;
	
	
}
