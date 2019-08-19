package com.example.demo.service;

import com.example.demo.dao.TestemojiMapper;
import com.example.demo.entity.Testemoji;
import com.example.utils.EmojiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.UnsupportedEncodingException;
import java.util.List;

import static com.example.utils.EmojiUtil.emojiConvertToUtf;

/**
 * @ClassName TestemojiService
 * @Description //TODO
 * @Author 123
 * @Date 2019/8/1 17:56
 * @Version 1.0
 * try catch代码块是处理emoji表情的，如果你的mysql修改了my.ini:添加了这两个配置
 * [mysql]
 * default-character-set=utf8
 * [mysqld]
 * character-set-server=utf8
 * 那么不需要处理，数据库也能储存emoji
 * 将try catch块注释掉即可。
 **/
@Service
public class TestemojiService {
    @Autowired
    private TestemojiMapper mapper;

    public void save(Testemoji testemoji) {
        try {
            //先将emoji转换
            testemoji.setEmoji(emojiConvertToUtf(testemoji.getEmoji()));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        testemoji.setId(null);
        mapper.insertSelective(testemoji);
    }

    public List<Testemoji> queryAll() {
        List<Testemoji> list = mapper.selectAll();
        //取出后转换emoji
        if(!CollectionUtils.isEmpty(list))
        for (Testemoji e:list){
            try {
                e.setEmoji(EmojiUtil.utfemojiRecovery(e.getEmoji()));
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }
        }
        return list;
    }

    public void deleteById(int id) {
        mapper.deleteByPrimaryKey(id);
    }
    //改
    public void updateById(Testemoji testemoji) {
        try {
            testemoji.setEmoji(EmojiUtil.emojiConvertToUtf(testemoji.getEmoji()));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        mapper.updateByPrimaryKeySelective(testemoji);
    }
}
