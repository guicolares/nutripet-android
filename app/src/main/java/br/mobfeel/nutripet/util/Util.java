package br.mobfeel.nutripet.util;

import java.util.Random;

import br.mobfeel.nutripet.R;

public class Util {
    public static int randomDogImage(){
        int[] images = {R.mipmap.dog1,R.mipmap.dog2, R.mipmap.dog3, R.mipmap.dog4, R.mipmap.dog5};
        Random r = new Random();
        return images[r.nextInt(4)];

    }
}
