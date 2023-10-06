package ua.in.sz.h2;

import lombok.extern.slf4j.Slf4j;
import org.codehaus.plexus.DefaultPlexusContainer;
import org.codehaus.plexus.PlexusContainer;

@Slf4j
public class Main {
    public static void main(String[] args) throws Exception {
        PlexusContainer container= new DefaultPlexusContainer();

        Cheese cheese = (Cheese) container.lookup( Cheese.ROLE, "parmesan" );
        cheese.print();

        Second second = (Second) container.lookup( Second.ROLE, "second-1" );
        second.print();

        Second other = (Second) container.lookup( Second.ROLE, "other-second-1" );
        other.print();

        container.dispose();
    }
}