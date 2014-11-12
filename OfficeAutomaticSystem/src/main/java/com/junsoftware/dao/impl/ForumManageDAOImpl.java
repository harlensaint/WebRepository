package com.junsoftware.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.junsoftware.dao.ForumManageDAO;
import com.junsoftware.domain.Forum;
import com.junsoftware.extra.ExtraDAOImpl;

@SuppressWarnings("all")
@Repository
public class ForumManageDAOImpl extends ExtraDAOImpl<Forum> implements
		ForumManageDAO {

	/**
	 * 重新父类的方法，实现排序查询
	 */
	public List<Forum> findAll() {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(Forum.class);
		detachedCriteria.addOrder(Order.asc("position"));
		List<Forum> list = getHibernateTemplate().findByCriteria(
				detachedCriteria);
		return list;
	}

	@Override
	public void moveUp(Forum model) {
		// 查找出此model在数据库中的实例
		Forum forum = findById(model.getId());
		// 获取此对象的上个对象
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(Forum.class);
		// String hql =
		// "FROM Forum f WHERE f.position < ? ORDER BY f.position DESC";
		// 添加小于判断的约束
		detachedCriteria.add(Restrictions.lt("position", forum.getPosition()));
		// 添加降序排序
		detachedCriteria.addOrder(Order.desc("position"));
		// 分页，去第一页，第一个（按每页一个记录分页）

		List<Forum> list = getHibernateTemplate().findByCriteria(
				detachedCriteria, 0, 1);

		if (list != null && list.size() > 0) {
			// 获取moder的上一个forum对象
			Forum upForum = list.get(0);

			// 互换两个的position值
			Integer current_Pos = forum.getPosition();
			Integer up_Pos = upForum.getPosition();

			// 中间变量
			int temp = current_Pos;
			forum.setPosition(up_Pos);
			upForum.setPosition(temp);
		}

	}

	@Override
	public void moveDown(Forum model) {
		// 查找出此model在数据库中的实例
				Forum forum = findById(model.getId());
				// 获取此对象的上个对象
				DetachedCriteria detachedCriteria = DetachedCriteria
						.forClass(Forum.class);
				// String hql =
				// "FROM Forum f WHERE f.position > ? ORDER BY f.position ASC";
				// 添加小于判断的约束
				detachedCriteria.add(Restrictions.gt("position", forum.getPosition()));
				// 添加降序排序
				detachedCriteria.addOrder(Order.asc("position"));
				// 分页，去第一页，第一个（按每页一个记录分页）

				List<Forum> list = getHibernateTemplate().findByCriteria(
						detachedCriteria, 0, 1);

				if (list != null && list.size() > 0) {
					// 获取moder的上一个forum对象
					Forum upForum = list.get(0);

					// 互换两个的position值
					Integer current_Pos = forum.getPosition();
					Integer up_Pos = upForum.getPosition();

					// 中间变量
					int temp = current_Pos;
					forum.setPosition(up_Pos);
					upForum.setPosition(temp);
				}

	}
}
