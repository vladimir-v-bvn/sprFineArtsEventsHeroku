package com.vber.sprFineArtsEvents.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.vber.sprFineArtsEvents.dto.AddYourEventDto;
import com.vber.sprFineArtsEvents.util.rsJSONConversion;

@Repository
public class EventRepositoryJdbc implements EventRepository {
  private org.slf4j.Logger LOG = LoggerFactory.getLogger(EventRepositoryJdbc.class);
  @Autowired
  private JdbcTemplate jdbcTemplate;
  private SimpleJdbcCall simpleJdbcCall;

  @Override
  public int vTopEventsCount() {
    return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM v_top_events", Integer.class);
  }
  
  @Override
  public String vTopEvents() {
    
    return jdbcTemplate.query("SELECT * FROM v_top_events_camel",  new VTopEventsExtractor());
  //return jdbcTemplate.query("SELECT * FROM v_top_events",  new VTopEventsExtractor());
  //return jdbcTemplate.query("select * from v_top_events", (rs, rowNum) -> new TopEvent(rs.getLong("id"), rs.getString("name"), rs.getBigDecimal("price")));
  }

  private static final class VTopEventsExtractor implements ResultSetExtractor<String> {
    @Override
    public String extractData(ResultSet resultSet) throws SQLException {
      return rsJSONConversion.convertResultSetToJSONArray(resultSet);
    }
  }

  public Map<String, Object> addYourEvent(AddYourEventDto addYourEventDto) {
    simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("add_your_event");
    Map<String, Object> out = simpleJdbcCall.execute(
      new MapSqlParameterSource("EVENT_NAME", addYourEventDto.getEventName())
                      .addValue("EVENT_DATE", addYourEventDto.getEventDate())
                      .addValue("LOCATION_ID", addYourEventDto.getLocationId()));
  //LOG.info("addYourEvent recordsInserted: " + (int) out.entrySet().iterator().next().getValue());
  //LOG.info("addYourEvent recordsInserted out: " + out);
    return out;
  }
  
}
