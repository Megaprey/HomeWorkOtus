package ru.otus.test;
import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.otus.pojo.MyGson;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MyGsonTest {
    @Test
    @DisplayName(" сравнение с Gson")
    void shouldEqualsWithGson() throws IllegalAccessException {
        MyGson myGson = new MyGson();
        Gson gson = new Gson();
        assertThat(myGson.toJson(null)).isEqualTo(gson.toJson(null));
        assertThat(myGson.toJson((byte)1)).isEqualTo(gson.toJson((byte)1));
        assertThat(myGson.toJson((short)1f)).isEqualTo(gson.toJson((short)1f));
        assertThat(myGson.toJson(1)).isEqualTo(gson.toJson(1));
        assertThat(myGson.toJson(1L)).isEqualTo(gson.toJson(1L));
        assertThat(myGson.toJson(1f)).isEqualTo(gson.toJson(1f));
        assertThat(myGson.toJson(1d)).isEqualTo(gson.toJson(1d));
        assertThat(myGson.toJson("aaa")).isEqualTo(gson.toJson("aaa"));
        assertThat(myGson.toJson('a')).isEqualTo(gson.toJson('a'));
        assertThat(myGson.toJson(new int[] {1, 2, 3})).isEqualTo(gson.toJson(new int[] {1, 2, 3}));
        assertThat(myGson.toJson(List.of(1, 2 ,3))).isEqualTo(gson.toJson(List.of(1, 2 ,3)));
        assertThat(myGson.toJson(Collections.singletonList(1))).isEqualTo(gson.toJson(Collections.singletonList(1)));
    }
}
