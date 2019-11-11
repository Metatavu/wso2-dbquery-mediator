# wso2-dbquery-mediator

WSO2 mediator for making simple multi-row queries into database. This mediator is greatly inspired by dbselect mediator (https://github.com/mephist/wso2-esb-dbselect)

mediator syntax is very similar to WSO2 dblookup mediator (https://docs.wso2.com/display/ESB500/DBLookup+Mediator).

Results are stored in DB_QUERY_RESULTS property.

## Example configuration

    <dbquery>
       <connection>
          <pool>
            <dsName/>
            <icClass/>
            <url/>
            <user/>
            <password/>
            <property name="name" value="value"/>*
          </pool>
       </connection>
       <statement>
          <sql>select something from table where something_else = ?</sql>
          <parameter [value="" | expression=""] type="CHAR|VARCHAR|LONGVARCHAR|NUMERIC|DECIMAL|BIT|TINYINT|SMALLINT|INTEGER|BIGINT|REAL|FLOAT|DOUBLE|DATE|TIME|TIMESTAMP"/>*
          <result name="string" column="int|string"/>*
       </statement>+
    </dbquery>


