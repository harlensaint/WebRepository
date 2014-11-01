package cn.itcast.ssh.domain;

/**
 * History entity. @author MyEclipse Persistence Tools
 */

public class History implements java.io.Serializable {

	// Fields

	private String id;
	private Goods goods;
	private String datetime;
	private String operatetype;
	private Double amount;
	private Double remain;
	private String operateruser;

	// Constructors

	/** default constructor */
	public History() {
	}

	/** minimal constructor */
	public History(String id) {
		this.id = id;
	}

	/** full constructor */
	public History(String id, Goods goods, String datetime, String operatetype,
			Double amount, Double remain, String operateruser) {
		this.id = id;
		this.goods = goods;
		this.datetime = datetime;
		this.operatetype = operatetype;
		this.amount = amount;
		this.remain = remain;
		this.operateruser = operateruser;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Goods getGoods() {
		return this.goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public String getDatetime() {
		return this.datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getOperatetype() {
		return this.operatetype;
	}

	public void setOperatetype(String operatetype) {
		this.operatetype = operatetype;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getRemain() {
		return this.remain;
	}

	public void setRemain(Double remain) {
		this.remain = remain;
	}

	public String getOperateruser() {
		return this.operateruser;
	}

	public void setOperateruser(String operateruser) {
		this.operateruser = operateruser;
	}

}