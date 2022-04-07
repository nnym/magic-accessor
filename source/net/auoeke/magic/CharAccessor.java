package net.auoeke.magic;

abstract class CharAccessor extends MagicAccessor {
    @Override
    public boolean getBoolean(Object object) {
        return this.getChar(object) != 0;
    }

    @Override
    public byte getByte(Object object) {
        return (byte) this.getChar(object);
    }

    @Override
    public short getShort(Object object) {
        return (short) this.getChar(object);
    }

    @Override
    public int getInt(Object object) {
        return this.getChar(object);
    }

    @Override
    public long getLong(Object object) {
        return this.getChar(object);
    }

    @Override
    public float getFloat(Object object) {
        return this.getChar(object);
    }

    @Override
    public double getDouble(Object object) {
        return this.getChar(object);
    }

    @Override
    public Character get(Object object) {
        return this.getChar(object);
    }

    @Override
    public void setBoolean(Object object, boolean value) {
        this.setChar(object, (char) (value ? 1 : 0));
    }

    @Override
    public void setByte(Object object, byte value) {
        this.setChar(object, (char) value);
    }

    @Override
    public void setShort(Object object, short value) {
        this.setChar(object, (char) value);
    }

    @Override
    public void setInt(Object object, int value) {
        this.setChar(object, (char) value);
    }

    @Override
    public void setLong(Object object, long value) {
        this.setChar(object, (char) value);
    }

    @Override
    public void setFloat(Object object, float value) {
        this.setChar(object, (char) value);
    }

    @Override
    public void setDouble(Object object, double value) {
        this.setChar(object, (char) value);
    }

    @Override
    public void set(Object object, Object value) {
        this.setChar(object, (char) value);
    }
}
