package cn.edu.jxnu.seckill.service

import scala.language.implicitConversions
import scala.collection.JavaConversions._
import scala.collection.immutable.{ List => _, _ }
import java.util.{ List => JavaList }
import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired
import cn.edu.jxnu.seckill.dao.GoodsDao
import cn.edu.jxnu.seckill.vo.GoodsVo
import cn.edu.jxnu.seckill.domain.SeckillGoods

/**
 * 商品服务层
 *
 * @author 梦境迷离.
 * @time 2018年5月19日
 * @version v1.0
 */
@Service
class GoodsService @Autowired() (val goodsDao: GoodsDao) {

    def listGoodsVo(): JavaList[GoodsVo] = goodsDao.listGoodsVo()

    def getGoodsVoByGoodsId(id: Long) = goodsDao.getGoodsVoByGoodsId(id)

    def reduceStock(goods: GoodsVo): Boolean = {
        var g = new SeckillGoods()
        g.setGoodsId(goods.getId())
        goodsDao.reduceStock(g) > 0
    }

    def resetStock(goodsList: List[GoodsVo]) {
        for (goods <- goodsList) {
            val g = new SeckillGoods()
            g.setGoodsId(goods.getId())
            g.setStockCount(goods.getStockCount())
            goodsDao.resetStock(g)
        }
    }

}