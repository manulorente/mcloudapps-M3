package es.codeurjc.users.restclients;

import es.codeurjc.users.dtos.responses.UserCommentResponseDto;

import java.util.Collection;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "commentsRestClient" ,url = "${users.comments.endpoint}")
public interface CommentsRestClient {

    @RequestMapping(method = RequestMethod.GET, value = "${users.comments.endpoint}")
    Collection<UserCommentResponseDto> getComments(@PathVariable("userId") long userId);

}
