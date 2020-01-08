package ua.in.sz.pattern.spring.camel.camel;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.component.file.remote.FtpComponent;
import org.apache.camel.spi.annotations.Component;

@Slf4j
@Component("ftp-list")
public class FtpListComponent extends FtpComponent {
}
