package com.hjt.geo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.GeoOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author hjt
 * @date:2023/3/23
 */
@Component
public class GeoUtil {

   @Autowired
   private RedisTemplate redisTemplate;

   private static final String GEO_KEY = "DISTANCE";

   /**
    * 将经纬度信息添加到redis中
    * @param certId 标识
    * @param longitude 经度
    * @param latitude 纬度
    */
   public void geoAdd(String certId, double longitude, double latitude) {
      GeoOperations geoOperations = redisTemplate.opsForGeo();
      Point point = new Point(longitude, latitude);
      RedisGeoCommands.GeoLocation geoLocation = new RedisGeoCommands.GeoLocation(certId, point);
      geoOperations.add(GEO_KEY, geoLocation);
   }

   /**
    * 两个人之间的距离
    * @param certId1
    * @param certId2
    * @return
    */
   public double distanceBetween(String certId1, String certId2) {
      GeoOperations geoOperations = redisTemplate.opsForGeo();
      Distance distance = geoOperations.distance(GEO_KEY, certId1, certId2);
      return distance.getValue();
   }

   /**
    * 查询距离某个人指定范围内的人，包括距离多少米
    * @param certId
    * @param distance
    * @return
    */
   public Map<String, Double> distanceInclude(String certId, double distance) {
      Map<String, Double> map = new LinkedHashMap<>();

      GeoOperations geoOperations = redisTemplate.opsForGeo();
      RedisGeoCommands.GeoRadiusCommandArgs geoRadiusCommandArgs = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs();
      GeoResults<RedisGeoCommands.GeoLocation<String>> geoResults = geoOperations.radius(GEO_KEY, certId, new Distance(distance), geoRadiusCommandArgs.includeDistance());
      if (geoResults != null) {
         Iterator<GeoResult<RedisGeoCommands.GeoLocation<String>>> iterator = geoResults.iterator();
         while (iterator.hasNext()) {
            GeoResult<RedisGeoCommands.GeoLocation<String>> geoResult = iterator.next();
            // 与目标点相距的距离信息
            Distance geoResultDistance = geoResult.getDistance();
            // 该点的信息
            RedisGeoCommands.GeoLocation<String> geoResultContent = geoResult.getContent();
            map.put(geoResultContent.getName(), geoResultDistance.getValue());
         }
      }
      return map;
   }




}