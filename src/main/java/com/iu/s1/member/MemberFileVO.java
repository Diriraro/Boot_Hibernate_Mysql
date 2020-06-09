package com.iu.s1.member;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data	//getter/setter
@Table(name = "memberFile")
@Entity
public class MemberFileVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //auto increment - mysql에서만
	private long fileNum;
	@Column
	private String fileName;
	@Column
	private String oriName;

	@OneToOne
	@JoinColumn(name = "id")
	private MemberVO memberVO;
	
}
