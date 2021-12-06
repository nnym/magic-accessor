package net.auoeke.magic;

abstract class BooleanAccessor extends MagicAccessor {
    @Override
    public byte getByte(Object object) {
        return (byte) (this.getBoolean(object) ? 1 : 0);
    }

    @Override
    public char getChar(Object object) {
        return (char) (this.getBoolean(object) ? 1 : 0);
    }

    @Override
    public short getShort(Object object) {
        return (short) (this.getBoolean(object) ? 1 : 0);
    }

    @Override
    public int getInt(Object object) {
        return this.getBoolean(object) ? 1 : 0;
    }

    @Override
    public long getLong(Object object) {
        return this.getBoolean(object) ? 1 : 0;
    }

    @Override
    public float getFloat(Object object) {
        return this.getBoolean(object) ? 1 : 0;
    }

    @Override
    public double getDouble(Object object) {
        return this.getBoolean(object) ? 1 : 0;
    }

    @Override
    public <T> T get(Object object) {
        return (T) (Boolean) this.getBoolean(object);
    }

    @Override
    public void setByte(Object object, byte value) {
        this.setBoolean(object, value != 0);
    }

    @Override
    public void setChar(Object object, char value) {
        this.setBoolean(object, value != 0);
    }

    @Override
    public void setShort(Object object, short value) {
        this.setBoolean(object, value != 0);
    }

    @Override
    public void setInt(Object object, int value) {
        this.setBoolean(object, value != 0);
    }

    @Override
    public void setLong(Object object, long value) {
        this.setBoolean(object, value != 0);
    }

    @Override
    public void setFloat(Object object, float value) {
        this.setBoolean(object, value != 0);
    }

    @Override
    public void setDouble(Object object, double value) {
        this.setBoolean(object, value != 0);
    }

    @Override
    public void set(Object object, Object value) {
        this.setBoolean(object, (boolean) value);
    }
}
