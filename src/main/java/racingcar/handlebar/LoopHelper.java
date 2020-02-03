package racingcar.handlebar;

import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;

import java.io.IOException;

public class LoopHelper implements Helper<Integer> {

    @Override
    public CharSequence apply(Integer context, Options options) throws IOException {
        StringBuilder sb = new StringBuilder();

        for(int i = 0 ; i <= context ; ++i) {
            sb.append('-');
        }

        return sb.toString();
    }
}
