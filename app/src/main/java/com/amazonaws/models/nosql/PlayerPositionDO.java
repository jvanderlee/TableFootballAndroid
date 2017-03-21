package com.amazonaws.models.nosql;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

import java.util.List;
import java.util.Map;
import java.util.Set;

@DynamoDBTable(tableName = "footballgameios-mobilehub-1595626962-playerPosition")

public class PlayerPositionDO {
    private String _id;
    private String _playerName;
    private float _positionX;
    private float _positionY;

    @DynamoDBHashKey(attributeName = "id")
    @DynamoDBAttribute(attributeName = "id")
    public String getId() {
        return _id;
    }

    public void setId(final String _id) {
        this._id = _id;
    }
    @DynamoDBRangeKey(attributeName = "playerName")
    @DynamoDBAttribute(attributeName = "playerName")
    public String getPlayerName() {
        return _playerName;
    }

    public void setPlayerName(final String _playerName) {
        this._playerName = _playerName;
    }
    @DynamoDBAttribute(attributeName = "PositionX")
    public float getPositionX() {
        return _positionX;
    }

    public void setPositionX(final float _positionX) {
        this._positionX = _positionX;
    }
    @DynamoDBAttribute(attributeName = "positionY")
    public float getPositionY() {
        return _positionY;
    }

    public void setPositionY(final float _positionY) {
        this._positionY = _positionY;
    }

}
