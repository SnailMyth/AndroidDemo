package com.example.testdemo.base;

import android.os.Build;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import okhttp3.Dns;

import static java.util.stream.Collectors.toList;


public class Ipv4DnsSelector implements Dns {

  private Map<String, List<InetAddress>> mOverrides = new HashMap<>();

  @Override
  public List<InetAddress> lookup(String hostname) throws UnknownHostException {
    List<InetAddress> addresses = mOverrides.get(hostname.toLowerCase());
    if (addresses != null) {
      return addresses;
    }
    addresses = Dns.SYSTEM.lookup(hostname);
    List<InetAddress> list = new ArrayList<>();
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
      list = addresses.stream().filter(Inet4Address.class::isInstance).collect(toList());
    } else {
      for (InetAddress address : addresses) {
        if (address instanceof Inet4Address) {
          list.add(address);
        }
      }
    }
    addOverride(hostname.toLowerCase(), list);
    return list;
  }

  private void addOverride(String hostname, List<InetAddress> list) {
    mOverrides.put(hostname.toLowerCase(), list);
  }
}
