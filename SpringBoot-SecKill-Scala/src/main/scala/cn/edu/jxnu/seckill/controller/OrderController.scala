package cn.edu.jxnu.seckill.controller

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.beans.factory.annotation.Autowired
import cn.edu.jxnu.seckill.service.OrderService
import cn.edu.jxnu.seckill.service.GoodsService
import cn.edu.jxnu.seckill.result.Result
import cn.edu.jxnu.seckill.domain.SeckillUser
import org.springframework.ui.Model
import cn.edu.jxnu.seckill.result.CodeMsg
import cn.edu.jxnu.seckill.vo.OrderDetailVo
import org.springframework.web.bind.annotation.RequestParam

/**
 * 订单控制器
 *
 * @author 梦境迷离.
 * @time 2018年5月21日
 * @version v1.0
 */
@RestController
@RequestMapping(Array("/order"))
class OrderController @Autowired() (orderService: OrderService,
    goodsService: GoodsService) {

    @RequestMapping(Array("/detail"))
    def info(model: Model, user: SeckillUser, @RequestParam("orderId") orderId: Long): Result[OrderDetailVo] = {

        if (user == null)
            Result.error(CodeMsg.SESSION_ERROR)
        val order = orderService.getOrderById(orderId)
        if (order == null)
            Result.error(CodeMsg.ORDER_NOT_EXIST)
        val goodsId = order.getGoodsId()
        val goods = goodsService.getGoodsVoByGoodsId(goodsId)
        val vo = new OrderDetailVo()
        vo.setOrder(order)
        vo.setGoods(goods)
        Result.success(vo)
    }

}