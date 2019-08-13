package com.cbr.cbrdemoone.Advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 设置一个 @ControllerAdvice用来监控 Multipart上传的文件大小是否受限，当出现此异常时在前端页面给出提示。
 *
 * 利用 @ControllerAdvice可以做很多东西，比如全局的统一异常处理等，可以了解。

 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {


    /**
     * 文件超出大小异常捕获
     * @param e
     * @param redirectAttributes
     * @return
     */
    @ExceptionHandler(MultipartException.class)
    public String handleError1(MultipartException e, RedirectAttributes redirectAttributes) {
        log.info("--------------捕获到了异常-handleError1----------------");
        redirectAttributes.addFlashAttribute("message", e.getCause().getMessage());
        log.info("--------------handleError1----------------");
        return "redirect:/uploadStatus";
    }

    /**
     * 文件超出大小异常捕获
     * 【注意，接收参数异常这里要携程跟捕获的一致，否则获取不到参数，会提示空指针：MaxUploadSizeExceededException】
     * @param e
     * @param redirectAttributes
     * @return
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public String handleError2(MaxUploadSizeExceededException e,RedirectAttributes redirectAttributes) {
        log.info("--------------捕获到了异常-handleError2----------------");
        redirectAttributes.addFlashAttribute("message", e.getMessage());
        log.info("--------------handleError2----------------");
        return "redirect:/uploadStatus";
    }


}
