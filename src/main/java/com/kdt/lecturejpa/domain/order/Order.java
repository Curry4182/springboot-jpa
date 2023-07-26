package com.kdt.lecturejpa.domain.order;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {

	@Id
	@Column(name = "id")
	private String uuid;
	@Column(name = "memo")
	private String memo;
	@Enumerated(value = EnumType.STRING)
	private OrderStatus orderStatus;
	@Column(name = "order_datetime", columnDefinition = "TIMESTAMP")
	private LocalDateTime orderDateTime;

	// member_fk
	@Column(name = "member_id", insertable = false, updatable = false)
	private Long memberId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "member_id", referencedColumnName = "id")
	private Member member;

	public void setMember(Member member) {
		if (Objects.nonNull(this.member)) {
			member.getOrders().remove(this);
		}

		this.member = member;
		member.getOrders().add(this);
	}
}
